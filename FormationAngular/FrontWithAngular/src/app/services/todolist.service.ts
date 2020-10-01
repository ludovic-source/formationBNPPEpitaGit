
export class ToDoListService {

    listeDesTaches = [
        {
            nom: '@Input / @Output',
            statut: 'done'
        },
        {
            nom: 'Les pipes',
            statut: 'todo'
        },
        {
            nom: 'Présenter Angular',
            statut: 'todo'
        },
        {
            nom: 'Le module',
            statut: 'todo'
        },
        {
            nom: 'Les directives',
            statut: 'todo'
        },
        {
            nom: 'Les composants',
            statut: 'todo'
        }
    ];

    constructor() {
    }

    getAllTaches() {
        return this.listeDesTaches;
    }

    addTache() {
        let tache = {nom: 'nouvelle tache ajoutée', statut: 'todo'};
        tache.nom = prompt("Quel est le nom de la tache ?");
        if (tache.nom != null && tache.nom != "") {
            this.listeDesTaches = [tache, ...this.listeDesTaches];
        } else {
            alert("Vous n'avez pas indiqué le nom de la tache");    
        }    
    }

    setTacheDone(nom: string) {
        console.log('tache réalisée : ' + nom);
        for (let tache of this.listeDesTaches) {
            if (tache.nom === nom) {
                tache.statut = 'done';
            }
        }
    }

    deleteTache(nom: string) {
        this.listeDesTaches = this.listeDesTaches.filter(tache => tache.nom != nom);
    }

    vider() {
        this.listeDesTaches.splice(0);
    }

}