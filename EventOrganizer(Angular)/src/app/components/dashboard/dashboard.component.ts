import { PlatformLocation } from '@angular/common';
import { Component, ElementRef, EventEmitter, NgZone, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

import { EventService } from 'src/app/service/event.service';




@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {

 
  message: String;
  addEventForm: FormGroup
  responseMessage: string;
  image = 'assets/images/ball.png';
  
  
 
  @Output() locationSelected = new EventEmitter<{ name: string, link: string }>();
  
  constructor(
    private service: EventService,
    private fb: FormBuilder,
    private router: Router,
    private ngZone: NgZone,
    private authService: AuthService
  ) {}

  ngOnInit() {

   

   
    
    this.addEventForm = this.fb.group({
      eventName:['', Validators.required],
      eventDate:['', Validators.required],
      eventTime:['', Validators.required],
      eventLocation:['', Validators.required],
      capacity: ['', [Validators.required, Validators.min(1)]],
    });

   

  }

  
  
 
  

  addEvent(){
    
    this.service.addEvent(this.addEventForm.value).subscribe((response)=>{
      console.log(response);
      this.responseMessage = 'An Event has been created successfully'
      
      
      },
      (error) => {
        console.error(error);
        this.responseMessage = 'Error creating the event. Please try again.';

    });
  }
  
  
  
  
}
