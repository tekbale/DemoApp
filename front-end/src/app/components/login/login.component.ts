import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router, RouterStateSnapshot } from '@angular/router';
import { UserValidityDto } from 'src/app/models/user-model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  error: any;

  constructor(private authService: AuthService, private fb: FormBuilder,
    private router: Router) {
      console.log("router data=",this.router.getCurrentNavigation()?.extras?.state);
     }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      password: new FormControl('', [Validators.required, Validators.minLength(3)])
    });
  }

  loginFormSubmit() {
    if(this.loginForm.invalid) 
      return;

    this.authService.authenticate(this.loginForm.value).subscribe({
      next: (userValidDto: UserValidityDto) => {
        if(userValidDto && userValidDto.isValidUser) {
          this.router.navigate(["home"]);
        } else {
          this.handleLoginError();
        }
      }, error: err => {
        this.handleLoginError();
      }
    });
  }

  private handleLoginError() {
    this.loginForm.setErrors({invalidCredentials: true, errorMsg: "Invalid Username/Password. Please try again."});
    this.router.navigate(["login"]);
  }
}
