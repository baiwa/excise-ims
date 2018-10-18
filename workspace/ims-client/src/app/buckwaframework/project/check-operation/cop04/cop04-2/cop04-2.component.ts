import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-cop04-2',
  templateUrl: './cop04-2.component.html',
  styleUrls: ['./cop04-2.component.css']
})
export class Cop042Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04020');
  }

}
