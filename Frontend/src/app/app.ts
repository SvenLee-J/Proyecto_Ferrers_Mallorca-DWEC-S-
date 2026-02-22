import { Component, signal, computed, effect } from '@angular/core';
import { RouterOutlet, RouterModule, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Auth } from './services/auth';
import { NavbarComponent } from './components/navbar/navbar';

// componente principal de la aplicación
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterModule,
    RouterLink,
    RouterLinkActive,
    NavbarComponent
  ],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  // título de la aplicación
  protected readonly title = signal('Ferrers Artesans');
  
  // estado del usuario
  userEmail = signal('');
  userRole = signal('');
  isLoggedIn = signal(false);

  // inicializa el efecto reactivo para sincronizar auth
  constructor(private auth: Auth) {
    effect(() => { 
      this.userEmail.set(this.auth.getEmail() || '');
      this.userRole.set(this.auth.getRole() || '');
      this.isLoggedIn.set(this.auth.isLoggedIn());
    });
  }
}
