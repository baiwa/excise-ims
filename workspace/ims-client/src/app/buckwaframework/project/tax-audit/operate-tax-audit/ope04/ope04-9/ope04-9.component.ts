import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-9',
  templateUrl: './ope04-9.component.html',
  styleUrls: ['./ope04-9.component.css']
})
export class Ope049Component implements OnInit {
  
  public showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04900');
  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
