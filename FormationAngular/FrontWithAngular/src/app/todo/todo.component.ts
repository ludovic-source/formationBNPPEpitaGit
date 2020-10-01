import { Component, OnInit, OnChanges, Input, EventEmitter, Output } from '@angular/core';
import { ToDoListService } from '../services/todolist.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit, OnChanges {

  @Input() listeDesTachesToDo: any[];
  @Output() doneEvent = new EventEmitter<any>();

  constructor(private toDoListService: ToDoListService) { }

  ngOnInit(): void {
      this.listeDesTachesToDo = this.listeDesTachesToDo.filter(tache => tache.statut == 'todo');
  }

  ngOnChanges(): void {
      console.log('changement todo');
      this.listeDesTachesToDo = this.listeDesTachesToDo.filter(tache => tache.statut == 'todo');
  }

  doneTache(nomTache: string) {
      this.toDoListService.setTacheDone(nomTache);
      this.doneEvent.emit(nomTache);
      this.listeDesTachesToDo = this.listeDesTachesToDo.filter(tache => tache.statut == 'todo');
      
  }

}
