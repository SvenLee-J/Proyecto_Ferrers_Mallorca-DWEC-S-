import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Auth } from '../../services/auth';

// componente de navegación principal
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css']
})
export class NavbarComponent implements OnInit {
  // rol del usuario actual
  userRole: string = '';

  constructor(public auth: Auth) {}

  // inicializa rol del usuario
  ngOnInit() {
    this.userRole = this.auth.getRole() || '';
  }

  // cierra sesión del usuario
  logout() {
    this.auth.logout();
  }
}
