import { Recu } from './recu.model';
export interface AP {
  gsm: string;
  nom: string;
  prenom?: string;
  email?: string;
  recus: Recu[];
}

