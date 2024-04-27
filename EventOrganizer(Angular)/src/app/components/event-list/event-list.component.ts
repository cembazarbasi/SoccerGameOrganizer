import { Component, HostListener, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

import { EventService } from 'src/app/service/event.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.scss']
})
export class EventListComponent {
  authService: AuthService;
  eventList: any[];
  users: any[];
  userEmail: string;
  fullName: string;
  image: 'src\assets\images\logopng.png';

  constructor(
    private eventService: EventService,
    private router: Router,

    private userService: UserService
  ) { }

  onEventClick(eventId: number): void {
    this.router.navigate([`/events/getEventById/${eventId}`]);
  }

  getRSVPCount(eventId: number): number {
    const rsvpList = localStorage.getItem(`rsvpList_${eventId}`);
    return rsvpList ? JSON.parse(rsvpList).length : 0;
  }

  ngOnInit() {
    console.log('AuthService in EventListComponent:', this.authService);

    this.fullName = localStorage.getItem('fullName') || '';
    console.log(this.fullName);

    this.userEmail = this.userService.userEmail;
    this.eventService.getEvents().subscribe((data: any[]) => {
      this.eventList = data;
    })
  }

  logout() {
    console.log('AuthService:', this.authService);
    if (this.authService) {
      this.authService.logout().subscribe(
        () => {
          console.log('Logout successful');
        },
        error => {
          console.error('Logout failed', error);
        }
      );
    } else {
      console.error('AuthService is undefined');
    }
  }
}
