import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'app-int06-11-3',
  templateUrl: './int06-11-3.component.html',
  styleUrls: ['./int06-11-3.component.css']
})
export class Int06113Component implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06113');
  }

  
}
