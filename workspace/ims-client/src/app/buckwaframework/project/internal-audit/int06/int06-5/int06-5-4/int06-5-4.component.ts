import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-5-4',
  templateUrl: './int06-5-4.component.html',
  styleUrls: ['./int06-5-4.component.css']
})
export class Int0654Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06540');
  }

}
