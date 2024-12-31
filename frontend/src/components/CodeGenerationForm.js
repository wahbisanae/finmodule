import React, { useState } from 'react';
import './CodeGenerationForm.css';
function CodeGenerationForm({ onGenerateCode }) {
  const [file, setFile] = useState(null);
  const [framework, setFramework] = useState('');
  const [error, setError] = useState('');

  // Gestion de la sélection de fichier
  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  // Gestion du changement de framework sélectionné
  const handleFrameworkChange = (e) => {
    setFramework(e.target.value);
  };

  // Gestion de l'envoi du formulaire
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Vérification si le fichier et le framework sont sélectionnés
    if (!file || !framework) {
      setError('Veuillez sélectionner un fichier et un framework.');
      return;
    }

    setError(''); // Réinitialisation de l'erreur
    const formData = new FormData();
    formData.append('file', file);
    formData.append('framework', framework);

    try {
      // Envoi de la requête POST avec le fichier et le framework
      const response = await fetch('http://localhost:8082/api/code/generate', {
        method: 'POST',
        body: formData,
      });

      if (!response.ok) {
        throw new Error('Erreur lors de la génération du code.');
      }

      // Récupération et affichage de la réponse
      const data = await response.text();
      onGenerateCode(data); // Appel de la fonction de rappel avec le code généré
    } catch (error) {
      console.error('Erreur lors de la soumission du formulaire :', error);
      setError('Une erreur est survenue. Veuillez réessayer plus tard.');
    }
  };

  return (
    <div className="form-container">
      <form onSubmit={handleSubmit}>
        <label>Choisissez un fichier YAML :</label>
        <input type="file" onChange={handleFileChange} />
        <label>Choisissez un framework :</label>
        <select value={framework} onChange={handleFrameworkChange}>
          <option value="">Sélectionnez un framework</option>
          <option value="springboot">Spring Boot</option>
          <option value="react">React</option>
          <option value="flask">Flask</option>
        </select>
        <button type="submit">Générer le code</button>
        {error && <p className="error-message">{error}</p>} {/* Affichage de l'erreur */}
      </form>
    </div>
  );
}

export default CodeGenerationForm;
