import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'int08-1',
  templateUrl: './int08-1.component.html',
  styleUrls: ['./int08-1.component.css']
})
export class Int081Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-08100');
  }

}
