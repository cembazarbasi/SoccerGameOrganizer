import { Component, NgZone, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  image = 'ball.png';

  signupForm: FormGroup | undefined;
  responseMessage: string;
  cities = [
    'Select Your City*',
    'San Francisco',
    'San Mateo',
    'Foster City',
    'San Bruno',
    'Burlingame',
    'Daly City',
    'San Jose',
    'Palo Alto',
    'Redwood City',
    'Sausalito',
    'San Ramon',
    'Santa Cruz',
    'Oakland',
    'Berkeley',
    'Emeryville',
    'Hayward',
    'Belmont',
    'Menlo Park',
    'South San Francisco'
  ].sort();
  errorMessage: string;

  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone
  ) { }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      city: ['', Validators.required],
      email: ['', Validators.required, Validators.email],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator })
  }

  passwordMatchValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const password = control.get('password').value;
    const confirmPassword = control.get('confirmPassword').value;

    return password === confirmPassword ? null : { 'passwordMismatch': true };
  }

  signup() {
    console.log(this.signupForm.value);

    this.service.signup(this.signupForm.value).subscribe(
      (response) => {
        console.log(response);
        this.responseMessage = 'Registration successful! Redirecting to login page...';
        setTimeout(() => {
          this.ngZone.run(() => {
            this.router.navigate(['/login']);
          });
        }, 3000);
      },
      (error) => {
        console.error(error);
        this.errorMessage = 'Registration failed. Please check your details or provide another email.';
      }
    );
  }
}