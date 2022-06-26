import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { ShiftDataDto, UserDto, ShiftStatus } from '../models/user-model';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(private http: HttpClient) { }

  getUserData(userId: string): Observable<UserDto> {
    return of({} as UserDto);
  }

  getShiftDataByUserId(userId: string): Observable<ShiftDataDto []> {
    const url = `${'http://localhost:8080/shift'}/${userId}`;
    return this.http.get<ShiftDataDto[]>(url)
    .pipe(
      map(response => {
        let data: ShiftDataDto[] = [];
        response.forEach((value:any) => {
          data.push({index: value.index, userId: userId,
            shiftStatus: value.shiftStatus as ShiftStatus, shiftRecordTime: value.createdOn } as ShiftDataDto);
        });
        return data;
      }),
      catchError(this.handleError('getShiftDataByUserId', []))
    );
  }

  udpateUserShift(action: UserDto): Observable<UserDto | {}> {
    return this.http.put<UserDto>('http://localhost:8080/shift', action);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
