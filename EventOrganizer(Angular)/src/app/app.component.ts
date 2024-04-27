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

  constructor(private authService: AuthService) { }

  logout() {
    this.authService.logout().subscribe(
      () => {
        console.log('Logout successful');
      },
      (error) => {
        console.error('Logout failed', error);
      }
    );
  }

}
