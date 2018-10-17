import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-epa02-1-2',
  templateUrl: './epa02-1-2.component.html',
  styleUrls: ['./epa02-1-2.component.css']
})
export class Epa0212Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('EXP-02120');
  }

}
