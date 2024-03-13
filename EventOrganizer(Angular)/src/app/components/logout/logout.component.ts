import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';


@Component({
  selector: 'app-logout',
  template: `
    <p>Logging out...</p>
  `,
})
export class LogoutComponent implements OnInit {
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.logout();
  }

  logout(): void {
    this.authService.logout().subscribe(
      (response) => {
        console.log('Logout successful');
        // Optionally, navigate to the login page or perform other actions
        this.router.navigate(['/login']);
      },
      (error) => {
        console.error('Logout failed', error);
        // Handle error, e.g., display an error message
      }
    );
  }
}
