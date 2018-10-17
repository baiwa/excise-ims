import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-4-4',
  templateUrl: './int06-4-4.component.html',
  styleUrls: ['./int06-4-4.component.css']
})
export class Int0644Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06440');
  }

}
