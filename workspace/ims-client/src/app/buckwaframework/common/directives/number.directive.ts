import { Directive, Input, ElementRef, HostListener } from '@angular/core';

@Directive({ selector: '[numberOnly]' })
export class NumberDirective {

    @Input() dash: boolean = false;

    constructor(private el: ElementRef) {
        console.log('numberOnly');
    }

    @HostListener("keypress", ['$event'])
    onkeypress(e) {
        let theEvent = e || window.event;
        let key;
        // Handle paste
        if (theEvent.type === 'paste') {
            key = theEvent.clipboardData.getData('text/plain');
        } else {
            // Handle key press
            key = theEvent.keyCode || theEvent.which;
            key = String.fromCharCode(key);
        }
        let regex = /[0-9]|\./;
        if (this.dash == true) {
            regex = /[0-9-]|\./;
        }
        if (!regex.test(key)) {
            theEvent.returnValue = false;
            if (theEvent.preventDefault) theEvent.preventDefault();
        }
    }

}