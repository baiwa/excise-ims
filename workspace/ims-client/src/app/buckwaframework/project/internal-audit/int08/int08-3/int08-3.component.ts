import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'int08-3',
  templateUrl: './int08-3.component.html',
  styleUrls: ['./int08-3.component.css']
})
export class Int083Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-08300');
  }

}
