import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable,  tap } from 'rxjs';


const BASE_URL = 'http://bayareasoccerevents.com';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private admin = {
    adminEmail: 'cembazar@gmail.com',
    adminPassword: 42171903
  };

  isLoggedIn(): boolean {
    
    const token = localStorage.getItem('JWT');
    return !!token;
  }  
  isAllowedForDashboard(userEmail: string): boolean {
    // Check if the userEmail is allowed to access the dashboard
    return userEmail === 'cembazar@gmail.com'; // Modify this condition as needed
  }

  constructor(private route: ActivatedRoute, private http: HttpClient) {
    this.route.params.subscribe(params => {
      const token = params['token'];
      
    });
  }

  
  getUserEmail(): string {
    return this.admin.adminEmail;
  };

  
  getUserPassword(): number {
    return this.admin.adminPassword;
  };


  requestPasswordReset(email: string): Observable<void> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
    });

    const body = new URLSearchParams();
    body.set('email', email);

    return this.http.post<void>(`${BASE_URL}/reset-password/request`, body.toString(), { headers });
  }

  showResetForm(token: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    const url = `${BASE_URL}/reset-password/reset`;
    return this.http.get<string>(url, { headers: headers });
  }



  resetPassword(token: string, newPassword: string): Observable<any> {
    const body = new URLSearchParams();
    body.set('token', token);
    body.set('newPassword', newPassword);

    const options = {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    };

    return this.http.post(`${BASE_URL}/reset-password/reset`, body.toString(), options);
  }


  signup(signupRequest: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',      
    });
    return this.http.post(BASE_URL + "/register", signupRequest, {headers} )
  }

  

  login(loginRequest: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',      
    });

    return this.http.post(BASE_URL + "/login", loginRequest,  {headers} ).pipe(
      tap(response => {
        if( response.jwtToken){
          
          localStorage.setItem('email', response.name);
          console.log('Email stored in localStorage:', response.email);
        }
      })
    );
   
  }


  
    logout(): Observable<any> {
      const logoutUrl = `${BASE_URL}/logout`;
      
      // Get the JWT token from localStorage
      const jwtToken = localStorage.getItem('JWT');
  
      // Include the Authorization header with the Bearer token
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${jwtToken}`
      });
  
      // Make the HTTP request with the headers
      return this.http.post(logoutUrl, {}, { headers });
    }

  

  getUserProfile(): Observable<any> {
    return this.http.get<any>(`${BASE_URL}/profile`);
  }
 

  

  
  hello(): Observable<any> {
    return this.http.get(BASE_URL + '/hello', {
      headers: this.createAuthorizationHeader()
    });
  }
  private createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('JWT');
    if (jwtToken) {
      return new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`);
    } else {
      console.log("JWT token not found in the Local Storage");
    }
    return null;
  }

  
}
