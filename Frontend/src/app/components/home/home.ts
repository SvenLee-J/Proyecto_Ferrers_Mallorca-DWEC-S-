import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { Auth } from '../../services/auth';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './home.html',
  styleUrls: ['./home.css']
})
export class HomeComponent implements OnInit {
  userEmail: string | null = ''; // Email del usuario logueado.
  userRole: string | null = ''; // Rol del usuario logueado.

  constructor(
    private auth: Auth,
    private router: Router // redirecciones
  ) {}

  ngOnInit(): void {
    this.userEmail = this.auth.getEmail?.() || ''; // Obtiene email del localStorage.
    this.userRole = this.auth.getRole?.() || ''; // Obtiene rol del localStorage.
  }

  logout(): void {
    this.auth.logout(); // Borra token y redirige a login.
  }

  navigateToUsers(): void {
    this.router.navigate(['/users']); // Navega a lista de usuarios.
  }
}
