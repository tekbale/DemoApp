import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { ShiftDataDto, ShiftStatus, UserDto } from 'src/app/models/user-model';
import { AuthService } from 'src/app/services/auth.service';
import { UserDataService } from 'src/app/services/user-data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewInit  {
  uid: string='';
  displayedColumns: string[] = ['index', 'shiftStatus', 'shiftRecordTime'];
  userData: UserDto[] = [];
  shiftData: ShiftDataDto[] = [];
  dataSourceWithPageSize:any;
  shiftChangeForm!: FormGroup;
  invalidActionError:boolean = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private userDataService: UserDataService, private fb: FormBuilder,
    private route: ActivatedRoute, private authService: AuthService) {
    this.shiftData = this.route.snapshot.data[0] as ShiftDataDto[];
    this.uid = this.authService.userid;
  }

  ngOnInit(): void {
    this.shiftChangeForm = this.fb.group({
      shiftChangeType: new FormControl('', Validators.required)
    });
    this.dataSourceWithPageSize = new MatTableDataSource(this.shiftData);
  }

  ngAfterViewInit() {
    this.resetPagination();
  }

  onSubmitAction() {
    if(this.shiftChangeForm.invalid) return;
    
    this.userDataService.udpateUserShift({userId: this.authService.userid, shiftStatus: this.shiftChangeForm.get("shiftChangeType")?.value as ShiftStatus} as UserDto)
      .subscribe({next: (response:any) => {
      }, complete: () => {
        this.getShiftData(this.authService.userid);
        this.resetForm();
      }, error: (err: any) => {
        this.shiftChangeForm.setErrors({invalidAction: true, errorMsg: "The shift action is invalid. Please try again!"});
      }});
  }

  private resetPagination() {
    this.dataSourceWithPageSize = new MatTableDataSource(this.shiftData);
    this.dataSourceWithPageSize.paginator = this.paginator;
  }

  private resetForm() {
    this.shiftChangeForm.reset();
  }

  private getShiftData(userId: string) {
    this.userDataService.getShiftDataByUserId(userId).subscribe(
      (data: ShiftDataDto[]) => {
        this.shiftData = data;
        this.resetPagination();
      }
    );
  }
}