import { Component, NgZone, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
})
export class ResetPasswordComponent implements OnInit{
  resetPasswordForm: FormGroup;
  token: string;
  newPassword: any;


  resetTokenData: any;
responseMessage: any;
errorMessage: any;


  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone
  ) {
    
    // Initialize the form with validators
    
  }
  ngOnInit(): void {
    this.resetPasswordForm = this.fb.group({
      newPassword: ['', Validators.required],
      confirmPassword : ['', Validators.required],
    }, { validator:this.passwordMatchValidator});

    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
      this.showResetForm();
    }) 
  }

  passwordMatchValidator(resetPasswordForm: FormGroup) {
    const newPassword = resetPasswordForm.get('newPassword')?.value;
    const confirmPassword = resetPasswordForm.get('confirmPassword')?.value;
  
    if (newPassword !== confirmPassword) {
      resetPasswordForm.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    } else {
      resetPasswordForm.get('confirmPassword')?.setErrors(null);
    }
  
    return null;
  }
  

  showResetForm() {
    this.authService.showResetForm(this.token).subscribe(
      data => {
        this.resetTokenData = data;
       
        // You can now use this.resetTokenData in your component as needed
      },
      error => {
        console.error(error); // Handle error
      }
    );
    
  }

  resetPassword() {
    if (this.resetPasswordForm.valid) {
      console.log('Token:', this.token);
      console.log('New Password:', this.resetPasswordForm.get('newPassword').value);
  
      this.authService.resetPassword(this.token, this.resetPasswordForm.get('newPassword').value).subscribe(
        response => {
          console.log(response);
  
          // Assuming your response has a success message, update accordingly
          this.responseMessage = 'Password reset successful!';
          setTimeout(() => {
            this.ngZone.run(() => {
              console.log('Redirecting to login page...');
              this.router.navigate(['/login']);
            });
          }, 5000);
        },
        error => {
          console.error(error);

          if (error.status === 200) {
          
            this.responseMessage = 'We sent a reset link through your email. Please go to your email.';
            setTimeout(() => {
              this.ngZone.run(() => {
                console.log('Redirecting to login page...');
                this.router.navigate(['/login']);
              });
            }, 5000);
          } else if (error.status === 400) {
            this.errorMessage = 'Failed to reset password. Please try again.';
          } else {
            
          }
  
          // Assuming your response has an error message, update accordingly
          
          // Handle error
        }
      );
    }
  }
  
}
