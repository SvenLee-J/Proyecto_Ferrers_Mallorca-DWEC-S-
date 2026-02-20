import { Component, signal, computed, effect } from '@angular/core';
import { RouterOutlet, RouterModule, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Auth } from './services/auth';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterModule,
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './app.html',  // ✅ FIX: app.html → app.component.html
  styleUrls: ['./app.css']    // ✅ FIX: styleUrl → styleUrls (plural)
})
export class AppComponent {
  // Signals para estado reactivo
  protected readonly title = signal('Ferrers Artesans');
  
  // Estado del usuario desde Auth service
  userEmail = signal('');
  userRole = signal('');
  isLoggedIn = signal(false);

  constructor(private auth: Auth) {
    // Effect para sincronizar Auth → UI
    effect(() => {
      this.userEmail.set(this.auth.getEmail() || '');
      this.userRole.set(this.auth.getRole() || '');
      this.isLoggedIn.set(this.auth.isLoggedIn());
    });
  }
}
