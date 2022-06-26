import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable, of } from 'rxjs';
import { ShiftDataDto } from '../models/user-model';
import { AuthService } from '../services/auth.service';
import { UserDataService } from '../services/user-data.service';

@Injectable({
  providedIn: 'root'
})
export class ShiftdataResolver implements Resolve<ShiftDataDto []> {
  constructor(private userDataService: UserDataService, private authService: AuthService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ShiftDataDto []> {
    return this.userDataService.getShiftDataByUserId(this.authService.userid);
  }
}
