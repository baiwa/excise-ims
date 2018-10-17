import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';

@Component({
  selector: 'ope04-4-1',
  templateUrl: './ope04-4-1.component.html',
  styleUrls: ['./ope04-4-1.component.css']
})
export class Ope0441Component implements OnInit {

  public showData: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('OPE-04410');

  }

  uploadData() {
    this.showData = true;
  }

  clearData() {
    this.showData = false;
  }

}
