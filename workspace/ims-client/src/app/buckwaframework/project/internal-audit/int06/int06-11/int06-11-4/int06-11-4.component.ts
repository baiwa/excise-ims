import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-11-4',
  templateUrl: './int06-11-4.component.html',
  styleUrls: ['./int06-11-4.component.css']
})
export class Int06114Component implements OnInit {

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06114');
  }

}
