import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-9-2',
  templateUrl: './int06-9-2.component.html',
  styleUrls: ['./int06-9-2.component.css']
})
export class Int0692Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06920');
  }

}
