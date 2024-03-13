import { PlatformLocation } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  

  loginForm: FormGroup;  
  image = 'assets/images/ball.png';
  image1 = 'background.jpg'
  email: string;
  showPassword = false;
  location: Location;


  constructor(
    private service: AuthService,
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
    this.userService.setUserEmail(this.userService.userEmail);
   
  }

  
  toggleShowPassword() {
    this.showPassword = !this.showPassword;
  }

  login() {

    
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe((response) => {
      console.log(response);
      
      if (response.jwtToken && response.fullName) {
        (response.jwtToken );
        const jwtToken = response.jwtToken;        
        localStorage.setItem('JWT', jwtToken);
       

        const fullName = response.fullName;
        localStorage.setItem('fullName', fullName);
        const userEmail = this.loginForm.get('email').value;
        this.userService.setUserEmail(userEmail);     
        this.router.navigateByUrl('/events');
        
        if (this.service.isAllowedForDashboard(userEmail)) {
          // Navigate to the dashboard if allowed
          this.router.navigateByUrl('/dashboard');
        } else {
          // Display a message or navigate to an unauthorized page
          console.error('User is not allowed to access the dashboard');
          this.router.navigate(['/events']);
        }
      }
    });
        
       
  
       
        
      
    }
   

  

}