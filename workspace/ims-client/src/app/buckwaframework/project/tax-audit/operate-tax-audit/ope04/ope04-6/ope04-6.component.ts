import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-6',
  templateUrl: './ope04-6.component.html',
  styleUrls: ['./ope04-6.component.css']
})
export class Ope046Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04600');

  }

}
