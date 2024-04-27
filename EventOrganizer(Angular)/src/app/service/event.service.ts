
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080'; 
@Injectable({
  providedIn: 'root',
})
export class EventService {
  getEventById(eventId: any) {
    return this.http.get(`${API_URL}/events/getEventById/${eventId}`);
  }  
  constructor(private http: HttpClient) {}
  
  addEvent(event: any): Observable<any> {
    return this.http.post(API_URL + "/dashboard", event);
  }  

  getEvents(){
    return this.http.get(API_URL + "/events");
  } 
}
