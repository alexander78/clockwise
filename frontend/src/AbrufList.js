import React, { Component } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class AbrufList extends Component {

    constructor(props) {
        super(props);
        this.state = {abrufe: []};
    }

    async componentDidMount() {

        fetch(`/api/abruf/getAllByProject/${this.props.match.params.projectid}`)
            .then(response => response.json())
            .then(data => this.setState({abrufe: data}));

    }

    render() {
        const {abrufe, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const AbrufList = abrufe.map(abruf => {
            return <tr key={abruf.id}>
                <td style={{whiteSpace: 'nowrap'}}>{abruf.abrufNummer}</td>
                <td>{abruf.rahmenBmNummer}</td>
                <td>{abruf.validFrom}</td>
                <td>{abruf.validTo}</td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/abruf/new">Add Abruf</Button>
                    </div>
                    <h3>Abrufe</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Abrufnummer</th>
                            <th width="20%">Rahmen-BM-Nr</th>
                            <th width="30%">Gültig von</th>
                            <th width="30%">Gültig bis</th>
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