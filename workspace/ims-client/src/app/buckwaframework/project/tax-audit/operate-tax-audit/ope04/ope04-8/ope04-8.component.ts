import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-8',
  templateUrl: './ope04-8.component.html',
  styleUrls: ['./ope04-8.component.css']
})
export class Ope048Component implements OnInit {

  constructor(
private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04800');
  }

}
