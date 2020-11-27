# clj-sparql

A Clojure library to provide a light-weight REST interface to:

- GraphDB RDF data store and SPARQL endpoint
- Apache Fuseki RDF data store and SPARQL endpoint
- AllegroGraph RDF data store and SPARQL endpoint
- DBPedia public RDF data store and SPARQL endpoint
- WikiData public RDF data store and SPARQL endpoint

## Usage

Edit the file `test/clj_sparql/core_test.clj` and uncomment the tests for the servers you have available.

Then try:

    lein test

and look at the source code for the unit tests. You need to have the SPARQL endpoints running.
In practice, you probably only have one SPARQL endpoint server that you are using so comment out the unit tests
for servers you don't have running.

DBPedia and WikiData are public services so those unit tests will run without
having a local SPARQL endpoint server.

## License

Copyright Mark Watson © 2020

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
