// user.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }
  userEmail: string = localStorage.getItem('email') || '';

  setUserEmail(email: string) {
    this.userEmail = email;
    localStorage.setItem('email', email);
  }
}


