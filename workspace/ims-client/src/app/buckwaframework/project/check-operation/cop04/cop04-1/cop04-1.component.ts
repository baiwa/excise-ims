import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-cop04-1',
  templateUrl: './cop04-1.component.html',
  styleUrls: ['./cop04-1.component.css']
})
export class Cop041Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04010');
  }

}
