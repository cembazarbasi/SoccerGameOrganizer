import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscribable } from 'rxjs';
import { EventService } from 'src/app/service/event.service';

import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.component.html',
  styleUrls: ['./event-detail.component.scss']
})
export class EventDetailComponent {

  event: any;  
  userEmail: string;
  rsvpList: string[]=[];
  responseMessage: string;
  eventList: any[];
  eventId:number;
  eventDetails: any;
  capacity:number;
  fullName: string;
  cancelMessage:string;
  


  constructor(
    private route: ActivatedRoute,
    private eventService: EventService,
    private userService: UserService
    ){}

    ngOnInit(): void {

      /*this.fullName=this.userService.fullName;*/
      this.userEmail = this.userService.userEmail;
      this.eventId = +this.route.snapshot.paramMap.get('id');
      this.eventService.getEventById(this.eventId).subscribe(
        (eventDetails) => {
          this.event = eventDetails; 
          if (!this.event) {
            console.error('Event not found');
          }
        },        
      );      
      const storedRsvpList = localStorage.getItem(`rsvpList_${this.eventId}`);
      this.rsvpList = storedRsvpList ? JSON.parse(storedRsvpList) : [];
      this.fullName = localStorage.getItem('fullName') || '';
      
      

    // Fetch event details using the eventId
    this.eventService.getEventById(this.eventId).subscribe(
      (event: any) => {
        if (event) {
          this.event = event;
        } else {
          // Handle the case where the event is not found
          console.error('Event not found');
        }
      }
    )}

  onRsvp() {
    
    const isUserAlreadyRsvped = this.rsvpList.includes(this.fullName);
    if (!isUserAlreadyRsvped) {
    this.rsvpList.push(this.fullName);  
    localStorage.setItem(`rsvpList_${this.eventId}`, JSON.stringify(this.rsvpList));
    this.responseMessage = '';
    this.cancelMessage=''
    
  }else{
    this.responseMessage='You are already a participant of this game!'
  }
 }
  rsvpData(rsvpData: any): string {
    throw new Error('Method not implemented.');
  }


 cancelRsvp() {
 
  this.rsvpList = this.rsvpList.filter(fullName => fullName !== this.fullName);  
  localStorage.setItem(`rsvpList_${this.eventId}`, JSON.stringify(this.rsvpList));
  this.cancelMessage='You have canceled your RSVP!'
  console.log('RSVP canceled.');
 
  this.responseMessage = '';
}
}
    


  