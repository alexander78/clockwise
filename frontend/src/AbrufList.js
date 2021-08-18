import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class AbrufList extends Component {

    constructor(props) {
        super(props);
        this.state = {abrufe: [], filtered: []};

        this.getValueInput = this.getValueInput.bind(this);
    }

    async componentDidMount() {

        fetch(`/api/abruf/getAllByProject/${this.props.match.params.projectid}`)
            .then(response => response.json())
            .then(data => this.setState({abrufe: data, filtered: data}));

    }

    getValueInput (evt) {
        const inputValue = evt.target.value;
        this.setState({ filtered: inputValue });
        this.filterNames(inputValue);
    }
      
    filterNames (inputValue) {
        // eslint-disable-next-line
        const { filtered } = this.state;
        const { abrufe } = this.state;
        this.setState({
          filtered: abrufe.filter(item => 
             item.abrufNummer.includes(inputValue)),
          });
    }

    render() {
        const {filtered, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const AbrufList = filtered.map(abruf => {
            return <tr key={abruf.id}>
                <td style={{whiteSpace: 'nowrap'}}>{abruf.abrufNummer}</td>
                <td>{abruf.rahmenBmNummer}</td>
                <td>{abruf.validFrom}</td>
                <td>{abruf.validTo}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"../abruf/" + abruf.id + "/"+this.props.match.params.projectid}>Edit</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to={"../abruf/new/" +this.props.match.params.projectid}>Add Abruf</Button>
                    </div>
                    <h3>Abrufe</h3>
                    <div className="float-right">
                        <input type="text" placeholder="Suche Nach AbrufNr..." onChange={ this.getValueInput }></input>
                    </div>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="15%">Abrufnummer</th>
                            <th width="15%">Rahmen-BM-Nr</th>
                            <th width="25%">Gültig von</th>
                            <th width="25%">Gültig bis</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {AbrufList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="secondary" tag={Link} to="/projects">Cancel</Button>
                    </div>
                </Container>
            </div>
                                                                                                                                                                              
    }
}

export default AbrufList;