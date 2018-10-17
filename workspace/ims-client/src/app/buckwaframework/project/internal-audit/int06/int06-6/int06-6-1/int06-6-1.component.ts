import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'int06-6-1',
  templateUrl: './int06-6-1.component.html',
  styleUrls: ['./int06-6-1.component.css']
})
export class Int0661Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06610');
  }

}
