import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
login() {
throw new Error('Method not implemented.');
}
  title = 'angulareventplanner';
  
  loginForm: FormGroup;

  constructor(private authService: AuthService) {}

  logout() {
    // Call your authentication service's logout method
    this.authService.logout().subscribe(
      () => {
        // Perform any additional actions after successful logout
        console.log('Logout successful');
      },
      (error) => {
        // Handle logout error
        console.error('Logout failed', error);
      }
    );
  }
  
}
