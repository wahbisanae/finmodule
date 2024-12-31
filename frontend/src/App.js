import React, { useState } from "react";
import CodeGenerationForm from './components/CodeGenerationForm';
import './App.css';
function App() {
  const [generatedCode, setGeneratedCode] = useState('');

  const handleCodeGeneration = (code) => {
    setGeneratedCode(code);
  };

  return (
    <div className="App">
      {/* Header avec une couleur unie et du texte centré */}
      <header className="App-header">
        <h1>YAML to Code Generator</h1>
      </header>

      {/* Formulaire pour générer le code */}
      <CodeGenerationForm onGenerateCode={handleCodeGeneration} />

      {/* Affichage du code généré */}
      {generatedCode && (
        <div className="generated-code">
          <h2>Code Généré :</h2>
          <pre>{generatedCode}</pre>
        </div>
      )}
    </div>
  );
}

export default App;
