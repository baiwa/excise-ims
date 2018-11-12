import { Component } from "@angular/core";
import { AuthService } from "services/auth.service";

declare var $: any;

@Component({
  selector: "page-home",
  templateUrl: "home.html",
  styleUrls: ["./home.css"]
})
export class HomePage {
  position: string;
  constructor(private authService: AuthService) {
    // TODO
    this.authService.reRenderVersionProgram("home").then(dtl => {
      console.log("dtl: ", dtl);
      this.position = dtl.position;
    });
  }
}
