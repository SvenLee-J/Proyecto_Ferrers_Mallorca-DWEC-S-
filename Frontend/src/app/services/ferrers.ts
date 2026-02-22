import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auth } from './auth';

export interface PerfilFerrer {
  id: number;
  userId: number;
  nombreCompleto: string;
  telefono: string;
  especialidad: string;
  estado: string;
}

// servicio para gestionar perfiles de ferrer
@Injectable({
  providedIn: 'root'
})
export class FerrerService {
  // endpoint de ferrers
  private readonly apiUrl = 'http://localhost:8080/api/ferrers';

  // inyección de dependencias
  constructor(
    private http: HttpClient,
    private auth: Auth
  ) {}

  // obtiene perfil del ferrer autenticado
  getMiPerfil(): Observable<PerfilFerrer> {
    return this.http.get<PerfilFerrer>(`${this.apiUrl}/perfil`, {
      headers: this.auth.getAuthHeaders()
    });
  }

  // actualiza perfil del ferrer autenticado
  updateMiPerfil(perfil: Partial<PerfilFerrer>): Observable<PerfilFerrer> {
    return this.http.put<PerfilFerrer>(`${this.apiUrl}/perfil`, perfil, {
      headers: this.auth.getAuthHeaders()
    });
  }

  // obtiene todos los perfiles (solo admin)
  getAllPerfiles(): Observable<PerfilFerrer[]> {
    return this.http.get<PerfilFerrer[]>(`${this.apiUrl}/perfiles`, {
      headers: this.auth.getAuthHeaders()
    });
  }
}
