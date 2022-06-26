import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  @Input("uid") uid!: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  logoutUser(): void {
    this.authService.logoutUser();
    this.router.navigate(["login"]);
  }
}
