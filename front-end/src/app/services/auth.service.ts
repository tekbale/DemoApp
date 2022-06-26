import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap } from 'rxjs';
import { UserValidityDto } from '../models/user-model';

@Injectable()
export class AuthService {
  private username!: string;
  private isAuthenticated: boolean = false;

  constructor(private http: HttpClient) { }

  public get userid(): string  {
    return this.username;
  }

  public authenticate(creds: any) : Observable<UserValidityDto> {
    return this.http.post<UserValidityDto>("http://localhost:8080/user/check", {userId: creds.username})
    .pipe(
      tap( (userValidityDto: UserValidityDto) => {
        if(userValidityDto.isValidUser === true) {
          this.isAuthenticated = true;
          this.username = userValidityDto.userId;
        }
      }),
      catchError((err: any) => {
        this.isAuthenticated = false;
        this.username = '';
        throw new Error('Error in authentication');
      })
    );
  }

  get isUserAuthenticated() : boolean {
    return this.isAuthenticated;
  }

  public logoutUser() {
    this.isAuthenticated = false;
  }
}
