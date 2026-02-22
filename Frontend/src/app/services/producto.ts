import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Auth } from './auth';

export interface Producto {
  id?: number;
  nombre: string;
  precio: number;
  stock: number;
  categoriaId: number;
  disponible?: boolean;
  imagen?: string;
}

// servicio para gestionar productos del ferrer
@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  // endpoint de productos
  private readonly apiUrl = 'http://localhost:8080/api/ferrers/productos';

  // inyección de dependencias
  constructor(
    private http: HttpClient,
    private auth: Auth
  ) {}

  // obtiene lista de productos del ferrer
  getMisProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl, {
      headers: this.auth.getAuthHeaders()
    });
  }

  // crea un nuevo producto
  crearProducto(producto: Omit<Producto, 'id'>): Observable<Producto> {
    return this.http.post<Producto>(this.apiUrl, producto, {
      headers: this.auth.getAuthHeaders()
    });
  }

  // actualiza un producto existente
  actualizarProducto(id: number, producto: Partial<Producto>): Observable<Producto> {
    return this.http.put<Producto>(`${this.apiUrl}/${id}`, producto, {
      headers: this.auth.getAuthHeaders()
    });
  }

  // elimina un producto
  eliminarProducto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, {
      headers: this.auth.getAuthHeaders()
    });
  }
}
