import { AP } from './ap.model';

export interface Zone {
  id: number;
  name: string;
  aps: AP[];
}
