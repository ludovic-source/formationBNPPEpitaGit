import { Component, OnInit, Input, OnChanges, EventEmitter, Output } from '@angular/core';
import { ToDoListService } from '../services/todolist.service';

@Component({
  selector: 'app-done',
  templateUrl: './done.component.html',
  styleUrls: ['./done.component.css']
})
export class DoneComponent implements OnInit, OnChanges {

  @Input() listeDesTachesDone: any[];
  @Output() deleteEvent = new EventEmitter<any>();

  constructor(private toDoListService: ToDoListService) { }

  ngOnInit(): void {
      this.listeDesTachesDone = this.listeDesTachesDone.filter(tache => tache.statut == 'done');
  }

  ngOnChanges(): void {
      console.log("changement done");
      this.listeDesTachesDone = this.listeDesTachesDone.filter(tache => tache.statut == 'done');
  }

  deleteTache(nomTache: string) {
      this.toDoListService.deleteTache(nomTache);
      this.deleteEvent.emit(nomTache);
      this.listeDesTachesDone = this.listeDesTachesDone.filter(tache => tache.statut == 'done');
  }

}
