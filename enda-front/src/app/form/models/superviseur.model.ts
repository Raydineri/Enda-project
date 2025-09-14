import { Zone } from './zone.model';

export interface Superviseur {
  id: number;
  nom: string;
  prenom?: string;
  email?: string;
  gsm?: string;
  zones: Zone[];
}
