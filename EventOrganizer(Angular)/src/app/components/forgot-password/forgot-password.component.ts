// forgot-password.component.ts
import { Component, NgZone } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
})
export class ForgotPasswordComponent {
  forgotPasswordForm: FormGroup;
  responseMessage: string;
  
  
  errorMessage: string;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private ngZone: NgZone ) {
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  requestPasswordReset() {
    // Ensure the email is taken from the form value
    const email = this.forgotPasswordForm.get('email').value;

    this.authService.requestPasswordReset(email).subscribe(
      response => {
        console.log('Password reset success:', response);
        this.responseMessage = 'We sent a reset link succesfully through your email.';
    
        
      },
      error => {
        console.error('Error sending password reset email', error);
    
        if (error.status === 200) {
          
          this.responseMessage = 'We sent a reset link through your email. Please go to your email.';
          setTimeout(() => {
            this.ngZone.run(() => {
              console.log('Redirecting to login page...');
              this.router.navigate(['/login']);
            });
          }, 5000);
        } else if (error.status === 400) {
          this.errorMessage = 'Please provide another email.';
        } else {
          
        }
      }
    );
    
  }
}
