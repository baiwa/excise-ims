import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'int03-1',
  templateUrl: './int03-1.component.html',
  styleUrls: ['./int03-1.component.css']
})
export class Int031Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-03100');
  }

}
