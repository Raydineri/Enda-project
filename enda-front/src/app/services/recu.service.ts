import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Superviseur } from '../form/models/superviseur.model';
import { Zone } from '../form/models/zone.model';
import { AP } from '../form/models/ap.model';
@Injectable({
  providedIn: 'root'
})
export class RecuService {
  private apiUrl = 'http://localhost:8082/api';

  constructor(private http: HttpClient) { }

  getSuperviseurs(): Observable<Superviseur[]> {
    return this.http.get<Superviseur[]>(`${this.apiUrl}/superviseurs`);
  }

  getZones(): Observable<Zone[]> {
    return this.http.get<Zone[]>(`${this.apiUrl}/zones`);
  }

  getAPs(): Observable<AP[]> {
    return this.http.get<AP[]>(`${this.apiUrl}/aps`);
  }

  submitRecu(data: FormData): Observable<any> {
    return this.http.post(`${this.apiUrl}/recus`, data);
  }
}
