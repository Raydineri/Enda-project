import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Superviseur } from './models/superviseur.model';
import { Zone } from './models/zone.model';
import { AP } from './models/ap.model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {
  superviseurs: Superviseur[] = [];
  zones: Zone[] = [];
  aps: AP[] = [];
  filteredZones: Zone[] = [];
  filteredAPs: AP[] = [];

  selectedSuperviseur?: number;
  selectedZone?: number;
  selectedApGsm?: string;

  numeroRecudebut?: number;
  numeroRecufin?: number;
  fileToUpload: File | null = null;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadSuperviseurs();
    this.loadZones();
  }

  loadSuperviseurs(): void {
    this.http.get<Superviseur[]>('http://localhost:8082/api/superviseurs')
      .subscribe(superviseurs => {
        this.superviseurs = superviseurs;
        console.log('Superviseurs:', superviseurs);
      }, error => {
        console.error('Erreur lors du chargement des superviseurs:', error);
      });
  }

  loadZones(): void {
    this.http.get<Zone[]>('http://localhost:8082/api/zones')
      .subscribe(zones => {
        this.zones = zones;
        console.log('Zones:', zones);
        this.aps = zones.flatMap(zone => zone.aps || []);
        console.log('All APs from Zones:', this.aps);
      }, error => {
        console.error('Erreur lors du chargement des zones:', error);
      });
  }

  onSuperviseurChange(): void {
    console.log('Selected Superviseur ID:', this.selectedSuperviseur);
    if (this.selectedSuperviseur !== undefined) {
      const id = Number(this.selectedSuperviseur);
      const superviseur = this.superviseurs.find(sup => sup.id === id);
      if (superviseur) {
        this.filteredZones = superviseur.zones || [];
        console.log('Filtered Zones:', this.filteredZones);
      } else {
        this.filteredZones = [];
      }
    } else {
      this.filteredZones = [];
    }
    this.selectedZone = undefined;
    this.filteredAPs = [];
    console.log('Filtered APs after Superviseur change:', this.filteredAPs);
  }

  onZoneChange(): void {
    console.log('Selected Zone ID:', this.selectedZone);
    if (this.selectedZone !== undefined) {
      const selectedZoneId = Number(this.selectedZone);
      const selectedZoneObject = this.filteredZones.find(zone => zone.id === selectedZoneId);
      if (selectedZoneObject) {
        this.filteredAPs = selectedZoneObject.aps || [];
        console.log('Filtered APs:', this.filteredAPs);
      } else {
        this.filteredAPs = [];
      }
    } else {
      this.filteredAPs = [];
    }
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.fileToUpload = file;
      console.log('File selected:', this.fileToUpload);
    }
  }

  onSubmit(): void {
    console.log('Form submitted');
    console.log('Selected AP:', this.selectedApGsm);
    console.log('AP GSM to send:', this.selectedApGsm);
    console.log('Reçu Debut:', this.numeroRecudebut);
    console.log('Reçu Fin:', this.numeroRecufin);
    console.log('File:', this.fileToUpload);

    // Validation
    if (!this.selectedApGsm) {
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'AP GSM est indéfini.',
      });
      return;
    }

    if (!this.numeroRecudebut || !this.numeroRecufin ||
      this.numeroRecudebut.toString().length !== 7 || this.numeroRecufin.toString().length !== 7) {
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Les numéros de reçu doivent avoir une longueur de 7.',
      });
      return;
    }
    if (!this.fileToUpload) {
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Veillez choisir une image .',
      });
      return;
    }

    const formData = new FormData();
    formData.append('superviseurId', this.selectedSuperviseur ? this.selectedSuperviseur.toString() : '');
    formData.append('zoneId', this.selectedZone ? this.selectedZone.toString() : '');
    formData.append('apGsm', this.selectedApGsm);
    formData.append('numeroRecudebut', this.numeroRecudebut ? this.numeroRecudebut.toString() : '');
    formData.append('numeroRecufin', this.numeroRecufin ? this.numeroRecufin.toString() : '');

    if (this.fileToUpload) {
      formData.append('upload', this.fileToUpload, this.fileToUpload.name);
    }

    const url = 'http://localhost:8082/api/recus';

    this.http.post(url, formData, {
      reportProgress: true,
      observe: 'events'
    }).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        if (event.total) {
          console.log(`Upload Progress: ${Math.round((event.loaded / event.total) * 100)}%`);
        }
      } else if (event.type === HttpEventType.Response) {
        console.log('Upload complete', event.body);
        Swal.fire({
          icon: 'success',
          title: 'Succès',
          text: 'Formulaire validé avec succès. ' +
            'Veillez verifier votre boite mail ',
        });
        this.resetForm();
      }
    }, error => {
      console.error('Erreur lors de la soumission du formulaire:', error);
      Swal.fire({
        icon: 'error',
        title: 'Erreur',
        text: 'Erreur lors de la soumission du formulaire. ' +
          'Veillez vérifier vos informations et réessayer.',
      });
    });
  }

  resetForm(): void {
    this.selectedSuperviseur = undefined;
    this.selectedZone = undefined;
    this.selectedApGsm = undefined;
    this.numeroRecudebut = undefined;
    this.numeroRecufin = undefined;
    this.fileToUpload = null;
    this.filteredZones = [];
    this.filteredAPs = [];
  }
}
