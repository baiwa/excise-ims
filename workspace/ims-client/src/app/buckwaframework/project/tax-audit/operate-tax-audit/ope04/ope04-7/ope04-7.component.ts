import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-7',
  templateUrl: './ope04-7.component.html',
  styleUrls: ['./ope04-7.component.css']
})
export class Ope047Component implements OnInit {

  constructor(
    private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04700');
  }

}
